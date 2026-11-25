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
		withCredentials([
        	    string(
                	credentialsId: 'sonar-token',
                	variable: 'SONAR_TOKEN'
            		)
        	]) {
            		sh '''
				mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                  		-Dsonar.projectKey=leave-service-2024SL70030 \
                  		-Dsonar.host.url=http://localhost:9000 \
                  		-Dsonar.token="$SONAR_TOKEN"
            		'''
        		}
    		}
	}

    }
}
