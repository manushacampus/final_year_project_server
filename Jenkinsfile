pipeline {
    agent any // Use any available agent
    stages {
        stage('Build') {
            steps {
                // Run your build commands
                sh 'echo "Building..."'
                // Add other build commands here
            }
        }
        stage('Talisman Check') {
            steps {
                // Run Talisman to check for sensitive data
                sh 'talisman -c' // This checks the current directory
            }
        }
        stage('Test') {
            steps {
                // Run your test commands
                sh 'echo "Running tests..."'
                // Add other test commands here
            }
        }
    }
    post {
        always {
            // Cleanup or notifications if needed
            echo 'Cleaning up...'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
