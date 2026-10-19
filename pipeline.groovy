pipeline {
    agent { node {
            label 'node1'
        }
     }
    stages {
        stage('installing web_server') {
            steps {
                echo "installing nginx service.."
                sh '''
                sudo apt-get update && sudo apt-get install nginx -y
                '''
                
                echo "nginx service successfully installed.."
            }
        }
        stage('update index file') {
            steps {
                echo "updating file .."
                sh '''
                echo "<h1>Welcome to Jenkins CI/CD server ...</h1>" | sudo tee /var/www/html/index.html
                '''
            }
        }
        stage('restart service') {
            steps {
                sh '''
                sudo systemctl start nginx
                sudo systemctl status nginx
                '''
                
                echo "pipeline is exicuted .."
            }
        }
    }
}