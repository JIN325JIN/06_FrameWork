//글쓰기 버튼 클릭 시
const insertBtn = document.querySelector("#insertBtn");

//글쓰기 버튼이 존재할 때 
if(insertBtn!=null){
  insertBtn.addEventListener("click",()=>{

    //get방식 요청 ( 글작성 할수 잇는 페이지로 이동시키기)

    //editBoard/1/inset

    location.href=`/editBoard/${boardCode}/insert`;

  });
}