pipeline {
    agent any

    environment {
	JAVA_HOME      = '/usr/lib/jvm/java-17-openjdk-17.0.13.0.11-4.el9.x86_64'
        SONAR_HOST_URL = 'http://localhost:9000'
        PROJECT_KEY    = 'leave-service-2024sl70030'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/MeenakshiSarkar2692/leave-service-2024sl70030.git'
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
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                          -Dsonar.projectKey=${PROJECT_KEY} \
                          -Dsonar.projectName=${PROJECT_KEY} \
                          -Dsonar.host.url=${SONAR_HOST_URL} \
                          -Dsonar.login=${SONAR_TOKEN} \
                          -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }
    }
}
