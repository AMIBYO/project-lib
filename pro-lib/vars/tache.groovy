def call(Map stageParams) {

        
    checkout([
        $class: 'GitSCM',
        branches: [[name:  stageParams.branch ]],
        userRemoteConfigs: [[ url: stageParams.url ]]
         
    ])
     node {
        git url:  stageParams.url

        stage("Compile") {
            bat "mvn compile"
        }

        stage("Test") {
            bat "mvn test"
        }
        stage("install") {
            bat "mvn install"
        }
              stage("package") {
            bat "mvn package"
        }
 
    }
  }
