pipeline {
    agent any // Use any available agent
    stages {
        stage('Build') {
            steps {
                script {
                    // Specify the Docker image for Gradle
                    def gradleImage = 'gradle:8.10.2'
                    docker.image(gradleImage).inside {
                        checkout scm // Checkout your code
                        sh 'gradle build' // Run the Gradle build
                    }
                }
            }
        }
    }
}
