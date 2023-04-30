# SCM (Source Code Manager)

------

SCM은 프로그래머 팀이 소스 코드를 관리하는 데 사용하는 소프트웨어 도구이다.

### Git을 사용하는 이유

Git을 사용하는 이유를 소스코드 관리로 알 고 있는데, 그것보다 중요한 것은 생태계를 이용하기 위함이다. NPM, PIP, NuGet, DockerHub 등 대부분의 패키지 생태계가 Git 과 GitHub에 연결되어 있다. CI/CD 또한 Git을 기반으로 소스를 가져오고 실행한다. 소스코드 관리는 아주 기본적인 역할이다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a576425-e051-4ca3-9a64-4e94d5f31dc1/Untitled.png)

Git Flow란? Vincent Driessen 가 제안한 브랜칭 전략이다. 컨벤션 (Convention) 보다는 컨센서스 (Consensus)에 가깝다. 이 브랜칭 전략은 개발을 하면서 소스코드를 관리하고 출시하기 위한 대부분의 경우에 해결책을 제시한다.

- master : 제품으로 출시되는 브랜치
- develop : 다음 출시 버전을 개발하는 브랜치
- release : 이번 출시 버전을 준비하는 브랜치
- feature : 기능을 개발하는 브랜치
- hotfix : 출시 버전(master)에서 발생한 버그를 수정하는 브랜치

개발 흐름은 다음과 같다.

> 최초에 master 를 생성한다.
>
> 이후 주 개발을 목적으로 하는 develop 브랜치를 생성한다.
>
> 새로운 기능을 추가하려면 feature 브랜치를 생성한다. 기능 개발이 완료되면 feature 브랜치는 develop 브랜치로 `merge` 한다.
>
> 이번 버전에 출시할 모든 작업들이 완료되면 develop에서 release 브랜치를 생성한다. release 브랜치에서 QA를 진행한다. QA 진행 동안은 release 브랜치에 직접 커밋한다. QA가 모두 끝나면 release 브랜치를 master와 develop에 모두 merge 한다.
>
> 혹시나 서비스를 하고 있는 master에서 문제가 생길 경우 HotFix 브랜치를 생성하고 문제 해결 후 master와 develop에 모두 merge 한다.

이런 식으로 관리하면 신규 개발이나 버그 수정, QA, Hot Fix를 동시에 진행하여도 소스가 꼬이지 않고 대처가 가능하다. 아시다시피 현실은 녹록치않기 때문에 아무리 소스만 브랜칭 한다고 해서 실제로 이대로 다 돌아가진 않는다. 특히 백엔드의 경우 관련된 서버와 환경까지 준비가 되어야 한다. 그럼에도 불구하고 이런 브랜칭 전략은 개발을 하면서 일어나는 다양한 상황에서도 어느 정도 대응이 가능하고 관리를 용히하게 할 수 있다.