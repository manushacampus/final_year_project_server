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
                script {
                    // Running Talisman
                    try {
                        sh 'talisman -s'
                    } catch (Exception e) {
                        echo "Talisman check failed, but continuing..."
                    }
                }
            }
        }
        stage('Generate Report') {
            steps {
                script {
                    // Convert JSON report to HTML (if needed)
                    sh 'talisman_report_to_html.sh' // Your script to convert JSON to HTML

                    // Convert HTML report to PDF
                    sh 'wkhtmltopdf talisman_report/talisman_reports/report.html talisman_report/talisman_reports/report.pdf'
                }
            }
        }
        stage('Archive PDF') {
            steps {
                // Archive the generated PDF
                archiveArtifacts artifacts: 'talisman_report/talisman_reports/report.pdf', allowEmptyArchive: true
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
