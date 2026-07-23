pipeline {
    agent any

    environment {
        APP_NAME     = 'payment-action'
        IMAGE_NAME   = 'payment-action:latest'
        DOCKER_IMAGE = 'guruuduttvashishta/payment-action:latest'
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
                sh "docker tag ${IMAGE_NAME} ${DOCKER_IMAGE}"
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    sh '''
                    echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                    docker push $DOCKER_IMAGE
                    '''
                }
            }
        }

        stage('Deploy to AWS EC2') {
            steps {
                sshagent(credentials: ['aws-ec2-key']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ec2-user@16.176.219.65 '
                    docker stop ${APP_NAME} || true
                    docker rm ${APP_NAME} || true
                    docker pull ${DOCKER_IMAGE}
                    docker run -d --name ${APP_NAME} -p 8080:8080 ${DOCKER_IMAGE}
                    '
                    """
                }
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
