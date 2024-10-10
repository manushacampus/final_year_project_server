pipeline {
    agent any
    environment {
        PATH = "/home/ubuntu/.sdkman/candidates/gradle/current/bin:${env.PATH}"  // Add Gradle to PATH
    }
    stages {
        stage('Check PATH') {
            steps {
                sh 'echo $PATH'  // Display the PATH in Jenkins console output
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
