def releaseVersion, releaseTicket, branchToCheckout, checkApprover

pipeline {
    agent {
        label 'linux_openjdk_11'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    tools{
        jdk 'OpenJDK11'
        maven 'maven-standard'
    }

    environment {
        //User Provided
        //Build & Nexus Related
        nexusGroupId                = 'ae.emaratech.vision-microservices'
        buildDirectoryAPI           = './platform-lookup-service/target/'
        buildFileAPI                = 'platform-lookup-service-0.0.1-SNAPSHOT.jar'
        nexusArtifactTypeAPI        = 'jar'
        nexusArtifactId             = 'platform-svc-lookup'
        nexusVersion                = ''

        buildDirectoryDB            = './Database/'
        buildFileDB                 = 'blacklist-db.zip'
        nexusArtifactTypeDB         = 'zip'

        //Sonar Related
        sonarProjectKey             = 'platform-svc-lookup'
        //sonarSources                = ''
        //sonarTests                  = ''
        //sonarBinaries               = ''
        //sonarLibraries              = ''
        //sonarExclusions             = ''

        //Environment Related
        mailTo                      = 'vision.core@emaratech.ae,devops.scm@emaratech.ae'
        
        //System Provided
        deploymentRepoName          = 'platform-svc-lookup'
        deploymentRepoAddress       = 'emt-devops-projects/platform-svc-lookup-devops.git'
        projectName                 = 'platform-svc-lookup'
        branchName                  = BRANCH_NAME.toLowerCase()
        quitPipeline                = false
        funtionalArea               = 'Vision - Platform-lookup'
        nexusRepo                   = 'maven-vision'
    }

    stages {
        stage ('Preparation') {
            steps {
                script {
                    tagName             = branchName
                    if (branchName == 'develop' || branchName == 'develop-test' ) {
                        tagName         = "planned-qmg-platform-lookup-service-${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
                    }
                    echo "branchName:${branchName}, tagName:${tagName}"
                    utils.abortPreviousRunningBuilds()
                    (branchToCheckout, tagName, quitPipeline)   = utils.getDeploymentEnv(branchName, tagName)
                    (releaseTicket, releaseVersion)             = git.getCommitMessage()

                    jiraIssueStatus                             = jira.getIssueStatusFromKey(releaseTicket)
                    checkApprover                               = check.deploymentCondition(branchToCheckout, jiraIssueStatus, tagName, true, releaseTicket, tagName, funtionalArea)
    
                    def strArray        = tagName.split('-')
                    versionNo           = strArray[strArray.size() - 2]
                    buildNo             = strArray[strArray.size() - 1]
                    nexusVersion        = branchToCheckout + '-' + versionNo + '-' + buildNo
                    nexusGroupIdFormat1 = nexusGroupId.replaceAll("\\.", "%2F")
                    nexusLinkMain       = "http://" + nexus.getNexusUrl('1') + "/#browse/browse:${nexusRepo}:${nexusGroupIdFormat1}%2F${nexusArtifactId}%2F${nexusVersion}"
                    nexusLinkApp        = nexus.getNexusUrl('2') + "${nexusRepo}/ae/emaratech/vision-microservices/${nexusArtifactId}/${nexusVersion}/${nexusArtifactId}-${nexusVersion}.${nexusArtifactTypeAPI}"
                    nexusLinkDb         = nexus.getNexusUrl('2') + "${nexusRepo}/ae/emaratech/vision-microservices/${nexusArtifactId}/${nexusVersion}/${nexusArtifactId}-${nexusVersion}.${nexusArtifactTypeDB}"

                    // This is only for testing purpose until devlopment is under testing 
                    //checkApprover = true
                    quitPipeline  = false
                    
//                    echo "branchToCheckout:${branchToCheckout}, tagName:${tagName}, quitPipeline:${quitPipeline}, funtionalArea:${funtionalArea}"
//                    echo "releaseTicket:${releaseTicket}, releaseVersion:${releaseVersion}, jiraIssueStatus:${jiraIssueStatus}, checkApprover:${checkApprover}"
                    echo "nexusLinkMain:${nexusLinkMain}, nexusLinkApp:${nexusLinkApp}, nexusLinkDb:${nexusLinkDb}, nexusRepo:${nexusRepo}"
                    echo "nexusGroupId:${nexusGroupId}, nexusGroupIdFormat1:${nexusGroupIdFormat1}"
                }
            }
        }
        stage('Build App') {
            when {
                expression { (quitPipeline == false && checkApprover == true) && tagName.contains('service') }
            }
            steps {
                script{
                    configFileProvider([configFile(fileId: '45930d83-a734-4155-a8ab-2c2523b14faf', variable: 'MAVEN_SETTINGS')]) {
                        buildCode.maven(WORKSPACE, 'clean install -U')
                        //sh 'mvn clean install -s $MAVEN_SETTINGS'
                    }

                    sh 'ls'

                }
            }
        }

        stage('Publish Artifacts') {
            when {
                expression { quitPipeline == false && checkApprover == true }
            }
            steps {
                script {
                    if (tagName.contains('service')) {
                        dir(buildDirectoryAPI) {
                            sh 'ls -lahr'
                            nexus.nexusUploader(nexusArtifactId, buildFileAPI, nexusArtifactTypeAPI, nexusGroupId, nexusRepo, nexusVersion)
                        }
                    }

                    jira.setReleaseTicketFields(releaseTicket, 'Nexus', nexusLinkMain)
                }
            }
        }

/*
        stage('Trigger Deployment Pipeline') {
            when {
                expression { quitPipeline == false && checkApprover == true }
            }
            steps {
                script {
                    
                     git.commitToAnotherRepo(deploymentRepoAddress, deploymentRepoName, branchToCheckout, tagName, releaseVersion, releaseTicket, nexusLinkMain, nexusLinkApp, nexusLinkDb)
                }
            }
        }

*/

/*
        stage('Static Code Analysis') {
            when {
                expression { quitPipeline == false && checkApprover == true }
            }
            steps {
                script {
                    sonar.mvnProject(sonarProjectKey, releaseTicket)
                }
            }
        }

*/


    }
 
    post {
 /*
        always {
            script {
                if (checkApprover == false) {
                    email.skipDeploymentReason("${projectName} Build", releaseTicket, tagName, true, mailTo)
                }
                if (checkApprover == true && currentBuild.result != 'ABORTED') {
                    email.notifier(currentBuild.result, releaseTicket, "${projectName} Build", tagName, mailTo)
                }
            }
        }
*/
        cleanup {
            script {
                utils.workspaceCleanup(WORKSPACE)
            }
        }
    }
    
}
