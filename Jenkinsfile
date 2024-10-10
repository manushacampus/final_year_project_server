pipeline {
    // Specify the agent for the pipeline, e.g., any available agent
    agent any 

    stages {
        stage('Test Docker') {
            steps {
                // Test Docker command to ensure Docker is installed and accessible
                sh 'docker --version'
            }
        }
        stage('Build') {
            steps {
                // Check Maven version to ensure it's installed
                sh 'mvn --version'

                // Clean and build the project, skipping tests for faster builds
                sh 'mvn clean install -DskipTests'
            }
        }
    }

    // Optional: add post actions, notifications, etc.
    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
