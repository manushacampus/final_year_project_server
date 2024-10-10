pipeline {
    agent {
        docker {
            image 'gradle:8.10.2'
            args '-v /var/run/docker.sock:/var/run/docker.sock' // Optional: only if you need Docker in the Gradle container
        }
    }
    stages {
        stage('Build') {
            steps {
                // Checkout your code
                checkout scm

                // Run Gradle build
                sh 'gradle build'
            }
        }
    }
}
