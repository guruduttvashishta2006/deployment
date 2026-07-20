pipeline {
    agent any

    environment {
        APP_NAME   = 'payment-action'
        IMAGE_NAME = 'payment-action:latest'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/guruduttvashishta2006/deployment.git'
            }
        }

        stage('Build & Test (JaCoCo)') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17-alpine'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17-alpine'
                    reuseNode true
                }
            }
            steps {
                sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=payment-action \
                    -Dsonar.host.url=http://sonarqube:9000 \
                    -Dsonar.login=admin \
                    -Dsonar.password=admin
                '''
            }
        }

        stage('Package JAR') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17-alpine'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME} .'
            }
        }

        stage('Docker Run') {
            steps {
                sh '''
                    docker stop ${APP_NAME} || true
                    docker rm ${APP_NAME} || true
                    docker run -d --name ${APP_NAME} -p 8080:8080 ${IMAGE_NAME}
                '''
            }
        }

    }

    post {
        success {
            echo '✅ Pipeline completed successfully! App running on port 8080.'
        }
        failure {
            echo '❌ Pipeline failed. Check logs above.'
        }
    }
}
