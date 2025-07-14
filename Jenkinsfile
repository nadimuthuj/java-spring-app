
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-toekn-new',
                    url: 'https://github.com/nadimuthuj/java-spring-app.git',
                    branch: 'main'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'whoami'
                sh 'docker build -t employee-directory:latest .'
            }
        }
        stage('Run Container') {
            steps {
                sh '''
                docker rm -f employee-app || true
                docker run -d --name employee-app -p 8082:8080 employee-directory:latest
                '''
            }
        }        
        stage('Test') {
            steps {
                echo 'Testing if the app is accessible...'
                script {
                    def response = sh(
                        script: "curl -s -o /dev/null -w \"%{http_code}\" http://192.168.1.105:8080/employees",
                        returnStdout: true
                    ).trim()
        
                    if (response != '200') {
                        error "App is not accessible. Got HTTP status: ${response}"
                    } else {
                        echo "App is accessible. HTTP 200 OK."
                    }
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        sh 'echo "$DOCKERHUB_PASSWORD" | docker login -u "$DOCKERHUB_USERNAME" --password-stdin'
                        sh 'docker tag employee-directory:latest $DOCKERHUB_USERNAME/employee-directory:latest'
                        sh 'docker push $DOCKERHUB_USERNAME/employee-directory:latest'
                    }
                }
            }
        }        
    }
}
