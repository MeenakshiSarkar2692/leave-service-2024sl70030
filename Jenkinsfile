pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure this matches your Jenkins Global Tool Configuration name (or remove if mvn is in PATH)
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        PROJECT_KEY    = 'leave-service-2024sl70030'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/MeenakshiSarkar2692/leave-service-2024sl70030.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test jacoco:report'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=${PROJECT_KEY} \
                          -Dsonar.projectName=${PROJECT_KEY} \
                          -Dsonar.host.url=${SONAR_HOST_URL} \
                          -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }
    }
}
