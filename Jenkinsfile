pipeline {

    agent {
        node {
            label 'jenkins-ec2-slave'
        }
    }

    tools { 
        maven 'maven3' 
    }

    options {
        buildDiscarder logRotator( 
                    daysToKeepStr: '15', 
                    numToKeepStr: '10'
            )
    }

    stages {
        
        
        stage('Code Build') {
            steps {
                 // Get some code from a GitHub repository
                git 'https://github.com/DEVK21/HrManagement.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }

        post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }

    }   
}
