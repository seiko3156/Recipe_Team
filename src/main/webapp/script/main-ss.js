function selectMyRecipeAll(selectAll)  {
  const checkboxes 
       = document.getElementsByName('rnum');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}

function go_favoriteindel(comm){
   /*let rnums = document.getElementsByName("rnums");
   console.log(rnums);*/
   
   let count=0;
   if(document.formm.rnum.length==undefined){
      if(document.formm.rnum.checked==true){
         count++;
      }
   }else{
      for(let i=0; i<document.formm.rnum.length; i++){
         if(document.formm.rnum[i].checked==true){
            count++;
         }
      }
   }
   if(count==0){
      alert("변환할 레시피를 선택하세요");
   }else {
      document.formm.action="recipe.do?command="+comm;
      document.formm.submit();
   }
   
}