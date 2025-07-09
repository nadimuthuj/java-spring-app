
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Docker Build') {
            steps {
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
    }
}
