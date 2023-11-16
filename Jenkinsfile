pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'wissal999'
        DOCKER_IMAGE_NAME = 'kaddem-project-wissal'
        CONTAINER_NAME= 'devops-kaddem-project'
        DOCKER_IMAGE_TAG = 'latest'
        PORT="9095"
    }

    stages {
        stage('GIT') {
            steps {
                echo "Getting Project from Git"
                git branch: 'WissalTOUATI-5SIM3-G2', credentialsId: '4201e22a-1f70-4aa8-bb76-7b8ecfa0e8d6', url: 'https://github.com/Lyndaabelhadj/5SIM3-G2-Kaddem.git'
            }
        }

        stage('MVN Clean') {
            steps {
                echo 'Cleaning...'
                sh 'mvn clean'
            }
        }

        stage('MVN Compile and testing') {
            steps {
                echo 'Validating...'
                sh 'mvn validate'
                echo 'Compiling...'
                sh 'mvn compile'
                echo 'Testing...'
                sh 'mvn clean test'
            }
        }

     stage('Code Coverage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn jacoco:report"
                }
            }
        }
        stage('Quality test SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=sonar -Dsonar.password=overlord'
                echo 'mvn -Sonarqube Analysis'
            }
        }

        stage('Maven Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy artifact with nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }


      stage('Build Docker image') {
            steps {
                sh "docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
            }
        }



        stage('Deploy image') {
                    steps {
                        echo "Deploying the image..."
                        sh 'docker login -u wissal999 -p overlord99 docker.io'
                        sh 'docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} '
                    }
                }


     stage("Docker compose") {
                     steps {

                          sh "docker compose up -d"
                           }
                         }

       /* stage('Maven deploy') {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }*/

        /*stage('Push Docker image to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credential-id', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh "sudo docker login -u ${username} -p ${password} ${DOCKER_REGISTRY}"
                    echo 'Pushing Docker Image to Nexus'
                    sh "sudo docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }
        */

    }

  post {
        success {
            mail bcc: '',
            body: 'The pipeline has completed successfully',
            from: 'ouissaltouaty99@gmail.com',
            subject: 'Jenkins pipeline completed successfully',
            to: 'ouissaltouaty99@gmail.com'
        }

        failure {
            mail bcc: '',
            body: 'The pipeline has failed',

            from: 'ouissaltouaty99@gmail.com', replyTo: '',
            subject: 'Jenkins pipeline failed', to: 'ouissaltouaty99@gmail.com'
        }

        always {
            emailext attachLog: true, body: '', subject: 'Build finished', from: 'ouissaltouaty99@gmail.com', to: 'ouissaltouaty99@gmail.com'
            cleanWs()
        }
    }

}
