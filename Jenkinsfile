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
stage('Build Docker Image') {
    steps {
        sh '''
            docker build \
                -t leave-service:${BUILD_NUMBER} \
                -t leave-service:latest .
        '''
    }
}

stage('Deploy to Staging') {
    steps {
        sh '''
            docker rm -f leave-service-staging || true

            docker run -d \
                --name leave-service-staging \
                -p 8085:8080 \
                -e APP_ENV=staging \
                leave-service:latest
        '''
    }
}

stage('Verify Staging Deployment') {
    steps {
        sh '''
            echo "Waiting for staging application..."

            for i in $(seq 1 15); do
                if curl -fsS http://localhost:8085/health; then
                    echo "Staging deployment successful!"
                    exit 0
                fi
                sleep 2
            done

            echo "Staging health check failed!"
            docker logs leave-service-staging
            exit 1
        '''
    }
}
    }
}
