pipeline {
    agent any

    environment {
        APP_NAME   = 'payment-action'
        IMAGE_NAME = 'payment-action:latest'
    }

    stages {

        stage('Checkout') {
            steps {
                cleanWs()
                git branch: 'main', url: 'https://github.com/guruduttvashishta2006/deployment.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package JAR') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Docker Run') {
            steps {
                sh """
                    docker stop ${APP_NAME} || true
                    docker rm ${APP_NAME} || true
                    docker stop deployment-app-1 || true
                    docker rm deployment-app-1 || true
                    docker run -d --name ${APP_NAME} -p 8080:8080 ${IMAGE_NAME}
                """
            }
        }

    }

    post {
        success {
            echo '✅ Pipeline completed! App running on port 8080.'
        }
        failure {
            echo '❌ Pipeline failed. Check logs above.'
        }
    }
}
