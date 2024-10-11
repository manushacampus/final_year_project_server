pipeline {
    agent any // Use any available agent
    stages {
        stage('Build') {
            steps {
                // Run your build commands
                sh 'echo "Building..."'
            }
        }
        stage('Talisman Check') {
            steps {
                // Adjust this command based on what you need
                sh 'talisman -s' // Scans for potential secrets
            }
        }
        stage('Test') {
            steps {
                // Run your test commands
                sh 'echo "Running tests..."'
            }
        }
    }
}
