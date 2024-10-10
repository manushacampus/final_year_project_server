pipeline {
    agent any
    stages {
        stage('Check PATH') {
            steps {
                sh 'echo $PATH'  // This will display the PATH in Jenkins console output
            }
        }
        stage('Build') {
            steps {
                sh 'gradle --version'  // Check if Gradle is available
                sh 'gradle build'  // Replace this with your actual build command
            }
        }
    }
}
