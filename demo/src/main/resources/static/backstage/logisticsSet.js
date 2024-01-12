let whichTd;
      
      function selected(){
        if (document.getElementById("logisticsPlatform").value!=0){
          document.getElementById("logisticsPlatform").style.backgroundColor=""
          document.getElementById("beDisable").disabled=true

        }
      }

      function clean(){
        document.getElementById("beDisable").disabled=false;
        document.getElementById("beDisable").selected=true;
        document.getElementById("setCOD").value="";
        document.getElementById("setRefriger").value=""

      }

      function writeTable(data){

        
        
        let count= $("#beWrite tr").length
        let a=data[0]==1?"賣家宅配":"超商店到店"
        let tr=
        `<tr id="tr_${count+1}"><th scope="row">${count+1}</th><td>${a}</td><td>一般宅配</td><td>${data[1]}元</td><td><button  onclick="set(${count+1})" class="btn btn-light" style="padding: 0px;"><img style="height: 16px; width: 16px;" src="./icon/revise.png" alt=""></button></td></tr>`
        let tr_f=
        `<tr id="tr_${count+2}"><th scope="row">${count+2}</th><td>${a}</td><td>冷藏宅配</td><td>${data[2]}元</td><td><button  onclick="set(${count+2})" class="btn btn-light" style="padding: 0px;"><img style="height: 16px; width: 16px;" src="./icon/revise.png" alt=""></button></td></tr>`

        if (data[2]==""){
          document.getElementById("beWrite").innerHTML=(
          document.getElementById("beWrite").innerHTML+tr
        )
        }else{
          document.getElementById("beWrite").innerHTML=(
          document.getElementById("beWrite").innerHTML+tr
          )
          
          document.getElementById("beWrite").innerHTML=(
          document.getElementById("beWrite").innerHTML+tr_f
          )
        }
        
      }

      function add(){
        let data=[]
        if (document.getElementById("logisticsPlatform").value==0){
          
          document.getElementById("logisticsPlatform").style.borderStyle="solid"
          document.getElementById("logisticsPlatform").style.borderColor="red"
          return
        }else{
          data.push(document.getElementById("logisticsPlatform").value)
        }
        
        
        if(document.getElementById("setCOD").value.match(/[0-9]+/)){
          data.push(document.getElementById("setCOD").value)
        }else if(document.getElementById("setCOD").value==""){
          data.push(60)
        } else{
          alert("請填數字")
          return
        }
        
        
        if(document.getElementById("setRefriger").value.match(/[0-9]+/)){
          data.push(document.getElementById("setRefriger").value)
        } else if (document.getElementById("setRefriger").value==""){
          data.push("")
        } else{
          alert("請填數字")
          
          return
        }

        

        writeTable(data);


      }

      function set(info){
        //info==第幾個tr
        document.getElementById("logisticsTable").style.display="none";
        document.getElementById("logisticsReset").style.display="inline-block"
        
        
        a= document.getElementById(`tr_${info}`)
        td= a.getElementsByTagName("td")
        // console.log(td[0].innerHTML)
        if(td[0].innerHTML=="賣家宅配"){
          document.getElementById("logisticsPlatformReset").value=1
          document.getElementById("logisticsPlatformReset").getElementsByTagName("option")[1].disabled=true
        }else{
          document.getElementById("logisticsPlatformReset").value=2
          document.getElementById("logisticsPlatformReset").getElementsByTagName("option")[0].disabled=true

        }
        if(td[1].innerHTML=="一般宅配"){
          document.getElementById("resetCOD").value=td[2].innerHTML.replace("元","")
          document.getElementById("resetRefri").disabled=true
        } else{
          document.getElementById("resetCOD").disabled=true
          document.getElementById("resetRefri").value=td[2].innerHTML.replace("元","")
        }
        whichTd=info
        
        


      }

      function cancel(){
        document.getElementById("logisticsTable").style.display="inline-block";
        document.getElementById("logisticsReset").style.display="none"
      }

      function save(){
        //info==第幾個tr
        a= document.getElementById(`tr_${whichTd}`)
        td= a.getElementsByTagName("td")
        

        if(document.getElementById("resetCOD").disabled==true){
          if(document.getElementById("resetRefri").value.match(/[0-9]+/)){
            td[2].innerHTML= document.getElementById("resetRefri").value+"元"
          }else{
            alert("請輸入數字")
            return
          }
          
        } else{
          if(document.getElementById("resetCOD").value.match(/[0-9]+/)){
            td[2].innerHTML= document.getElementById("resetCOD").value+"元"
          }else{
            alert("請輸入數字")
            return
          }
          cancel()
        }
      }