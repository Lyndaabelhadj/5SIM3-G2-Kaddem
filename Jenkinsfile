pipeline {
    agent any



    stages {

               stage('GIT'){
                   steps{
                   echo "Getting Project from Git";
                   git branch: 'WissalTOUATI-5SIM3-G2', credentialsId: '4201e22a-1f70-4aa8-bb76-7b8ecfa0e8d6',  url: 'https://github.com/Lyndaabelhadj/5SIM3-G2-Kaddem.git'
                   }
               }


           stage('MVN Clean') {
               steps {
                   echo 'Cleaning...'
                   sh "mvn clean"
               }
           }
           stage('MVN Complie') {
               steps {
                   echo 'Validating...'
                   sh "mvn validate"
                   echo 'Compiling...'
                   sh "mvn compile"
               }
           }

       stage('Quality test SONARQUBE') {
                   steps {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123'
                       echo 'mvn -Sonarqube Analysis'
                   }
               }

       /* stage('Build Docker image') {
            steps {
                sh "sudo docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
            }
        }

        stage('Maven deploy'){
            steps{
                sh "mvn deploy -DskipTests"
            }
        }

        stage('Push Docker image to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credential-id', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh "sudo docker login -u ${username} -p ${password} ${DOCKER_REGISTRY}"
                    echo 'Pushing Docker Image to Nexus'
                    sh "sudo docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }*/


    }

}