#!groovy
node("master") {
    ansiColor('xterm') {

        def mvnHome = tool 'mvn'
        def mvnJdk8Image = "orientdb/mvn-gradle-zulu-jdk-8"

        stage('Source checkout') {

            checkout scm
        }

        stage('Run tests on Java8') {
            docker.image("${mvnJdk8Image}").inside("${env.VOLUMES}") {
                try {
                    sh "${mvnHome}/bin/mvn -f driver/pom.xml  --batch-mode -V -U -e -fae clean  deploy -Dsurefire.useFile=false"

                    slackSend(color: '#00FF00', message: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")

                } catch (e) {
                    currentBuild.result = 'FAILURE'
                    slackSend(channel: '#jenkins-failures', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                    throw e;
                } finally {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml'

                }
            }
        }
    }

}
