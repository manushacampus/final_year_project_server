/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.9-eclipse-temurin-21-alpine' } }

    stages {
        stage('Build') {
            steps {
                // Print Maven version
                sh 'mvn --version'
                
                // Run Maven build and skip tests
                sh 'mvn clean package -DskipTests'
            }
        }
    }
}
