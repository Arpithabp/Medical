pipeline{
	agent any
	tools{
		maven 'M2_HOME'
		terraform 'TERRAFORM_HOME'
	}
	stages{
		stage('Git-CheckOut'){
			steps {
				echo 'CheckOut the source code form github'
				git branch: 'main', url: 'https://github.com/Aditya3328/Healthcare-Domain.git'
			}
		}
		stage('Package the app'){
			steps{
				echo 'Packaging the app....'
				sh 'mvn clean package'
			}
		}
		stage('publish reports using html'){
			steps{
				publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: '/var/lib/jenkins/workspace/Healthcare-Domain/target/surefire-reports', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
			}
		}
		stage('Docker Image Creation'){
			steps{
				sh 'docker system prune -af '
				sh 'docker build -t aditya3328/medicure:latest .'
			}
		}
		stage('DockerLogin'){
			steps{
					withCredentials([usernamePassword(credentialsId: 'Docker-Hub', passwordVariable: 'dockerHubPass', usernameVariable: 'dockerHubUser')]) {
					sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass}"
				}
			}
		}
		stage('push Image to DockerHub'){
			steps{
				sh 'docker push aditya3328/medicure:latest'
			}
		}
		
		stage('create infra'){
		   	dir('Terraform-Files')steps{
				sh 'sudo chmod 600 AssignmentKey.pem'
				sh 'terraform init'
				sh 'terraform validate'
				sh 'terraform apply --auto-approve'
			}
		}
		stage('Deploy app using ansible'){
			steps{
			ansiblePlaybook credentialsId: 'ssh-key', disableHostKeyChecking: true, installation: 'Ansible_home', inventory: '/var/lib/jenkins/workspace/Finance-pipeline/inventory', playbook: 'deploy.yml'
			}
		}
	}
}
