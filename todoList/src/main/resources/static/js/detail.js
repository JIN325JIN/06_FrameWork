//목록으로 버튼 동작( 메인페이지로 이동)
const goToList = document.querySelector("#goToList");
goToList.addEventListener("click",()=>{

location.href="/";
//메인페이지로 ("/")로 요청 
//location은 get방식
});

//완료여부 변경 버튼에 대한 동작
const completeBtn = document.querySelector(".complete-btn");
completeBtn.addEventListener("click",(e)=>{


  //요소.dataset: data-* 속성에 저장된 값 반환
  //= todoNo반환
  //data-todo-no에 세팅한 속성값 얻어오기
  //(html)data-todo-no -> js(카멜케이스)dataset.todoNo
  const todoNo=e.target.dataset.todoNo;
  

  //기존의 완료여부 값 얻어오기 ("Y"/"N")
  let complete = e.target.innerText;


  //Y<->N
  complete = (complete==='Y')?'N':'Y';
  //Y라면 N, 아니라면 Y
  //===은 타입까지 같은지 확인


  //완료 여부 수정 요청하기(백틱)
  location.href = `/todo/changeComplete?todoNo=${todoNo}&complete=${complete}`;


});

//-------------------------------------------------------------------
//수정버튼 클릭시 동작 
const updateBtn = document.querySelector("#updateBtn");
updateBtn.addEventListener("click",e=>{
  
  const todoNo = e.target.dataset.todoNo;

  location.href=`/todo/update?todoNo=${todoNo}`;
  //get방식 요청


});

//삭제 버튼 클릭시 동작
const deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click",e=>{

  const todoNo=e.target.dataset.todoNo;
  
    if (confirm("정말 삭제하시겠습니까?")) {
      // 확인 눌렀을 때만 삭제 진행
      location.href = `/todo/delete?todoNo=${todoNo}`;
    }

});