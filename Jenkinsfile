pipeline {
    agent { docker { image 'maven:3.9.9-eclipse-temurin-21-alpine' } }

    stages {
        stage('Test Docker') {
            steps {
                // Test Docker command
                sh 'docker --version'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean install -DskipTests'
            }
        }
    }
}
