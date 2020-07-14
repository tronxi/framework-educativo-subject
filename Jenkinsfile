pipeline {
    agent any
    environment {
        SUBJECT_TAG = '0.0.3'
        DOCKER_HUB_PASSWORD = credentials('DOCKER_HUB_PASSWORD')
    }
    stages {
        stage('Test') {
            steps {
                sh '''
                    mvn test
                '''
            }
        }
        stage('Build') {
            steps {
                sh '''
                    docker build -t tronxi/framework-educativo-subject:${SUBJECT_TAG} .
                '''
            }
        }
        stage('Push') {
            steps {
                sh '''
                    docker login  --username tronxi --password $DOCKER_HUB_PASSWORD
                    docker push tronxi/framework-educativo-subject:${SUBJECT_TAG}
                '''
            }
        }
        stage('Deploy') {
            steps {

                sh '''
                    export PATH=/root/google-cloud-sdk/bin:$PATH
                    gcloud container clusters get-credentials framework-educativo-cluster --zone europe-west1-b --project framework-educativo
                    envsubst < deploy.yml > deploy-env.yml
                    kubectl apply -f deploy-env.yml
                '''
            }
        }

    }
}