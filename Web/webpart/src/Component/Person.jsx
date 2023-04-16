import Card from './Card'
function PersonList(){
    const List=[
      
      {  id:1,
        Name:"ABHISHEK KUMAR",
        url:"https://instagram.com/abhishekroy8735?igshid=ZDdkNTZiNTM=",
        url1:"https://www.linkedin.com/in/abhishek-kumar-6328a221a",
        img:"fotor_2023-4-16_11_20_50.png"
      },
      {
        id:2,
        Name:'RITESH SINGH',
        url:'https://play.google.com/',
        url1:'https://play.google.com/',
        img:"fotor_2023-4-16_11_26_22.png"
      },
      {
        id:3,
        Name:'ATUL TOPPO',
        url:'https://instagram.com/atul_toppo56037?igshid=ZDdkNTZiNTM=',
        url1:'https://www.linkedin.com/in/atul-toppo-b190a3229',
        img:"fotor_2023-4-16_11_50_33.png"

      },
      {
        id:4,
        Name:'SHREYANSH KUMAR',
        url:'https://play.google.com/',
        url1:'https://play.google.com/',
        img:"fotor_2023-4-16_15_50_22.png"

      }
    ]

    return(
    <>
    {List.map((data)=><Card key={data.id} name={data.Name} Url={data.url} Img={data.img} Url1={data.url1}/>)}
   
    </>
    )
  }
   
export  default PersonList;