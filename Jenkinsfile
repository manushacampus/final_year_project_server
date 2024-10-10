pipeline {
    agent any
    environment {
        GRADLE_HOME = '/home/ubuntu/.sdkman/candidates/gradle/current'
        PATH = "${GRADLE_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Check Gradle Version') {
            steps {
                sh 'gradle --version'
            }
        }
        stage('Build') {
            steps {
                // Replace 'build' with your actual Gradle task if needed
                sh 'gradle build'
            }
        }
    }
}
