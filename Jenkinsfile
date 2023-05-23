pipeline {

    agent {
		label 'ec2-jenkins-slave-1'
	}

	environment {
		PATH = "/usr/share/apache-maven/bin:$PATH"
	}
    tools {
	  maven 'maven3'
	  git 'git'
	  jdk 'openjdk 11'
	}

    options {
	  retry(conditions: [agent()], count: 2)
	}

    stages {
	
	    stage('Git pull') {
            steps {
                 // Get some code from a GitHub repository
                git 'https://github.com/DEVK21/HrManagement.git'

            }
        }
		stage('build project') {
            steps {
                // Run Maven on a Unix agent.
                sh "mvn clean install"
            }
        }
        stage('junit test') {
            steps {
                // Run junit test case.
                junit keepLongStdio: true, testResults: 'target/surefire-reports/*.xml'
            }
        }
        stage('deploy to test') {
            steps {
                // deloyment to jenkins-slave-deployment node.
                sshagent(['deployment-agent']) {
                    
                    //if process previous deployment is running then kill the process
                     sh 'ssh -o StrictHostKeyChecking=no ec2-13-126-63-197.ap-south-1.compute.amazonaws.com /home/ec2-user/springboot-application/stop.sh'
                    
                    //copy the jar from ec2-jenkins-slave-1 agent to deployment-agent
                    sh 'scp -o StrictHostKeyChecking=no target/HrManagement-0.0.1-SNAPSHOT.jar ec2-user@13.126.63.197:/home/ec2-user/springboot-application/'
                    
                    //run the copied jar on deployment-agent 
                    sh 'ssh -o StrictHostKeyChecking=no ec2-13-126-63-197.ap-south-1.compute.amazonaws.com /home/ec2-user/springboot-application/start.sh'
                    
                }
            }
        }
	  
	  
	}
	
}
