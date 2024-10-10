pipeline {
    agent any
    environment {
        GRADLE_HOME = '/home/ubuntu/.sdkman/candidates/gradle/current'
        PATH = "${GRADLE_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle --version'
            }
        }
    }
}
