# CASCADE 옵션

Cascade는 특정 Entity의 영속성 상태가 변경됐을 때, 이를 연관된 Entity에도 전파시킬지 선택하는 옵션이다. @OneToMany 나 @ManyToOne 에 옵션으로 줄 수 있다.



### 종류

- CascadeType.PERSIST
  - JPA에서 엔티티를 저장할 때 연관된 모든 엔티티는 영속 상태여야 함.
  - 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화시킴.
- CascadeType.MERGE
  - 엔티티 상태 병합 시, 연관된 엔티티도 함께 병합.
- CascadeType.REFRESH
  - 엔티티를 새로 고칠 때, 연관된 엔티티도 새로고침.
- CascadeType.REMOVE
  - 엔티티 삭제 시, 연관된 엔티티도 함께 삭제.
- CascadeType.DETACH
  - 부모 엔티티가 detach()를 수행하면, 연관된 엔티티도 detach() 상태가 되어 변경사항 반영되지 않음.
- CascadeType.ALL
  - 모든 Cascade 적용.



##### ✔︎ 참고

- [JPA 자식 엔티티가 변경되지 않을 때 (feat. cascade)](https://prohannah.tistory.com/132)