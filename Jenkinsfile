pipeline {
   agent any

 stages {

       stage('Hello Linda') {
           steps {
               echo 'Hello World'
           }
       }
       stage('Clean') {
           steps {
               echo 'Cleaning...'
               sh "mvn clean"
           }
       }
       stage('Build') {
           steps {
               echo 'Validating...'
               sh "mvn validate"
               echo 'Compiling...'
               sh "mvn compile"
           }
       }
       stage('testin maven') {
           steps {
                sh 'mvn -DskipTests package'
               echo 'mvn -version'
           }
       }
       stage('Quality test SONARQUBE') {
           steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
               echo 'mvn -Sonarqube Analysis'
           }
       }
       stage('Deploy artifact with nexus') {
                   steps {
                        sh 'mvn deploy -DskipTests'
                   }
               }

/*
       stage('Docker Build image') {
              steps {
                sh 'docker build -t lindabelhadj/achat:1-0 .'

                    }
               }

       stage('Docker PUSH image') {
             steps {
       sh 'docker login -u lindabelhadj -p dock@hello123!!Lin'
       sh 'docker push lindabelhadj/achat:1-0'

        }
           }
           */
   }
   post {
         success{
         mail bcc: '', body: '''Dear Lynda ,
    we are happy to inform you that your pipeline build was successful.
    Great work !
    -Jenkins Team-''', cc: '', from: 'lynda.belhadj@esprit.tn', replyTo: '', subject: 'Build Finished - Success', to: 'lynda.belhadj@esprit.tn'
         }

         failure{
    mail bcc: '', body: '''Dear Lynda,
    we are sorry to inform you that your pipeline build failed.
    Keep working !
    -Jenkins Team-''', cc: '', from: 'lynda.belhadj@esprit.tn', replyTo: '', subject: 'Build Finished - Failure', to: 'lynda.belhadj@esprit.tn'
         }

           always {
        emailext attachLog: true, body: '', subject: 'Build finished',from: 'lynda.belhadj@esprit.tn' , to: 'lynda.belhadj@esprit.tn'
                cleanWs()
           }
        }
}

