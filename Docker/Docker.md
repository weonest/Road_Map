# Docker

### 도커란?

- 간단히 말해 도커란 리눅스 운영체제에 컨테이너를 만들고 사용할 수 있는 컨테이너화 기술이다. 도커 컨테이너는 이미지를 통해 실행되는데, 이 이미지는 도커 컨테이너가 실행되기 위한 종속성을 모두 가지고 있다. 따라서, 도커가 있는 어느 환경에서라도 도커 이미지를 통해 같은 컨테이너를 이용할 수 있다.
- 어플리캐이션을 패키징 할 수 있는 툴, Container 안에 Application, System Tools, Dependencies 를 하나로 묶어서 다른 서버, 다른 PC로 쉽게 배포하고 안전적으로 구동할 수 있게 도와준다.

### 탄생 배경

- 개발자 PC > 서버로의 애플케이션 배포 과정에서 버전이 맞지 않으면 문제가 발생하는데, 이러한 문제 및 번거로움을 해결하기 위해 탄생. (내 PC에선 되는데 왜 서버에선 안돼!?)

### Virtual Machine 과의 차이점

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/59167b72-842f-448b-a338-3b0307686597/Untitled.png)

- VM과 달리 가상의 Guest OS를 설치하지 않기 때문에 더욱 가볍게 관리가 가능하다.
- 여기서 **Docker**가 Container Engine에 속한다.

## 도커의 3대 구성요소!(순서)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/539b463e-7d04-4ad1-96ad-cffb664754b7/Untitled.png)

- Image는 DockerFile을 캡쳐한다고 생각하면 좋다. (Image는 불변성을 띈다)
- Image는 클래스, Container는 Image를 통해 만들어진 Instance라고 생각하면 좋다.

> 참조 : https://www.youtube.com/watch?v=LXJhA3VWXFA