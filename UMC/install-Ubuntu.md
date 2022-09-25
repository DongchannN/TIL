## Virtual Box 설치하기

1. https://www.virtualbox.org 에 접속하여 Virtual Box를 다운로드 받고 설치를 실행한다.

2. https://ubuntu.com 에 접속하여 Ubuntu를 다운받는다.

   > <img src="/Users/dongchan/Desktop/스크린샷 2022-09-23 오후 11.42.50.png" alt="스크린샷 2022-09-23 오후 11.42.50" style="zoom:40%;" />
   >
   > 홈페이지에서 다운로드를 누르면 설치가 느리다고 하니 아래의 "see our alternative downloads"에 들어가서 다운을 받았다.
   >
   > <img src="/Users/dongchan/Desktop/스크린샷 2022-09-23 오후 11.43.47.png" alt="스크린샷 2022-09-23 오후 11.43.47" style="zoom:40%;" />
   >
   > Other images and mirrors 배너 하단의 "See all Ubuntu mirrors"를 클릭해 밑의 Korea의ㅣ Kakao Corp을 클릭하여 다운을 받는다.

3. VM에 Ubuntu 설치하기.

   > ![스크린샷 2022-09-22 오후 10.33.16](/Users/dongchan/Desktop/스크린샷 2022-09-22 오후 10.33.16.png)
   >
   > VirtualBox를 실행하였을 때 보이는 화면이다. 새로 만들기를 이용해 우분투 가상환경을 만들 것이다.
   >
   > ![스크린샷 2022-09-22 오후 10.34.36](/Users/dongchan/Desktop/스크린샷 2022-09-22 오후 10.34.36.png)
   >
   > 이름을 입력하고 계속 버튼을 누른다.
   >
   > ![스크린샷 2022-09-22 오후 10.39.00](/Users/dongchan/Desktop/스크린샷 2022-09-22 오후 10.39.00.png)
   >
   > 우분투 홈페이지에서 다운 받은 ubuntu-18.04.6-desktop-amd64.iso파일을 넣어준다.
   >
   > ![스크린샷 2022-09-22 오후 10.51.28](/Users/dongchan/Desktop/스크린샷 2022-09-22 오후 10.51.28.png)
   >
   > VirtualBox의 안내를 따라주며 설치를 한다.
   >
   > 설치 형식에서 파티션들을 나누어 주어야 하는데 /dev/sda를 더블클릭하여 나눌 수 있다.
   >
   > 초기에 한글을 선택한 경우 아래 화면이 잘려서 보였다. 영어로 다시 설정을 한 후 정상적으로 모두 보여 설정을 완료했다.

4. Ubuntu에 Apache 설치

   > Ubuntu 설치를 끝냈으니 Apache를 컴파일 설치해보자.
   >
   > 먼저 Ubuntu의 터미널에서 사전에 설치해주어야 할 패키지들이 있다.
   >
   > ```
   > //관리자 권한으로 변경.
   > $sudo su
   > 
   > # apt-get install make
   > # apt-get install build-essential
   > # apt-get install gcc
   > # apt-get install --reinstall make
   > # apt-get install libexpat1-dev
   > # apt-get install g++ 
   > # apt-get install net-tools
   > # apt-get install curl
   > ```
   >
   > 이제 소스코드들을 다운 받아 설치할 것이다.
   >
   > 소스코드를 다운 받을 때에는 **1) 압축을 해제하고** **2. ./configure 설정** **3. make && make install 로 컴파일 후 설치**하면 된다.
   >
   > 나는 pcre, apr, apr-util, apache 순으로 설치를 진행하였다.
   >
   > ---
   >
   > - pcr
   >
   > ```
   > //pcre압축파일 다운로드
   > # wget https://sourceforge.net/projects/pcre/files/pcre/8.45/pcre-8.45.tar.gz/
   > 
   > //pcre압축파일 추출
   > # tar xvfz pcre-8.45.tar.gz
   > 
   > //pcre 컴파일 설치
   > # cd pcre-8.45/
   > # ./configure --prefix=/usr/local/pcre
   > # make && make install
   > ```
   >
   > ![스크린샷 2022-09-24 오후 10.09.50](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-09-24 오후 10.09.50.png)
   >
   > ###### pcre, apr, apr-util 등을 설치하면 위와 비슷한 화면이 뜬다.
   >
   > ---
   >
   > - apr과 apr-util 또한 위와 같은 방법으로 설치해주면 된다.
   >
   > ```
   > //apr configure
   > # ./configure --prefix=/usr/local/apr
   > //apr-utill configure
   > # ./configure --with-apr=/usr/local/apr --prefix=/usr/local/apr-util 
   > ```
   >
   > 
   >
   > ##### apr 설치 시 에러 (cannot remove 'libtoolT' : No such file or directory)
   >
   > 아래의 코드를 입력 한 후 재설치하여 해결하였다.
   >
   > ```
   > # cp -arp libtool libtoolT
   > ```
   >
   > ---
   >
   > - Apache 설치 (추가적으로 apache라는 폴더는 /usr/local 에 미리 만들어 놓았다.)
   >
   > ```
   > //다운로드와 압축해제는 생략.//
   > 
   > # ./configure --prefix=/usr/local/apache --enable-module=so --enable-rewrite --enable-so --with-apr=/usr/local/apr --with-apr-util=/usr/loacl/apr-util --with-pcre=/usr/local/pcre --enable-mods-shared=all
   > 
   > ## make && make install
   > ```
   >
   > 
   >
   > - Apache 작동 확인
   >
   > **httpd -k start** 명령어를 이용해 서버를 시작시켜 확인한다.
   >
   > ```
   > /usr/local/apache/bin# httpd -k start
   > ```
   >
   > ![스크린샷 2022-09-24 오후 10.31.08](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-09-24 오후 10.31.08.png)
   >
   > 
   >
   > ```
   > #netstat -anp | grep httd
   > ```
   >
   > 위의 명령어를 실행시키고 아래의 화면을 보면 홈페이지에 **It works!**라는 화면이 뜬다는 것을 볼 수 있다.
   >
   > ![스크린샷 2022-09-24 오후 10.32.07](/Users/dongchan/Library/Application Support/typora-user-images/스크린샷 2022-09-24 오후 10.32.07.png)

   

