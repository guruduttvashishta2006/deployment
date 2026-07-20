pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5'
        jdk 'JDK 17'
    }

    environment {
        // Update these credentials IDs to match your Jenkins configuration
        DOCKER_CREDENTIALS_ID = 'docker-hub-credentials'
        DOCKER_IMAGE_NAME     = 'your-docker-username/payment-action:latest'
        SONAR_SERVER_NAME     = 'SonarQube' // Name configured in Jenkins system settings
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Building and running tests with JaCoCo coverage...'
                sh 'mvn clean test'
            }
            post {
                always {
                    // Record JUnit test results and publish JaCoCo coverage
                    junit '**/target/surefire-reports/*.xml'
                    recordCoverage(tools: [[parser: 'JACOCO', pattern: '**/target/site/jacoco/jacoco.xml']])
                }
            }
        }

        stage('SonarQube Quality Gate') {
            steps {
                echo 'Running SonarQube Code Quality scan...'
                withSonarQubeEnv(SONAR_SERVER_NAME) {
                    sh 'mvn sonar:sonar'
                }
                // Optional: Force build failure if SonarQube quality gate fails
                // timeout(time: 1, unit: 'HOURS') {
                //     waitForQualityGate abortPipeline: true
                // }
            }
        }

        stage('Package Application') {
            steps {
                echo 'Packaging Jar file...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    echo 'Building Docker container...'
                    dockerImage = docker.build("${DOCKER_IMAGE_NAME}")
                    
                    echo 'Pushing Docker image to registry...'
                    docker.withRegistry('', DOCKER_CREDENTIALS_ID) {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Expose with ngrok or deploy command
                // sh 'docker-compose up -d --build'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please inspect logs.'
        }
    }
}
