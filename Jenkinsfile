pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'gradle --version'  // This should now work
                sh 'gradle build'  // Your build command
            }
        }
    }
}
