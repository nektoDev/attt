# Auto-Torrent-to-Transmission service

Automatically send magnet link to transmission when it changes. 
 Works with rutracker only.


 ## Start
 `gradle bootRun`
 
 ## Build
 ### Simple:
 `gradle build`
 
 ### Releases:
 `gradle releaseMajor` - build, up major version, build Docker image and push it to hub
 `gradle releaseMinor` - build, up minor version, build Docker image and push it to hub
 `gradle releasePatch` - build, up version, build Docker image and push it to hub

 ### Docker
 #### Start ATTT:
 ```
 docker pull nektodev/attt
 docker build --restart=always --net=container:vpn --name=attt --link mongodb:mongodbhost nektodev/attt
 docker start attt
 ```
 #### expose ports for service via vpn
 ```
 sudo docker run -it --name web  --restart=always -p 8081:80 --link vpn:attt -d dperson/nginx -w "http://attt:8080/;/attt"
 ```