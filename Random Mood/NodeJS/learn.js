// function getData(){
//     const pro = new Promise((resolve, reject) => {
//         const data = fetch('https://jsonplaceholder.typicode.com/posts')
        
//         if(data) resolve(JSON.stringify(data));
//         else reject(new Error("Failed to fetch data"));
        
//     });
//     return pro;
// }

// getData().then((data) => {
//     console.log(data);
// }).catch((err) => {
//     console.log(err);
// });



// function testPromise(x){
//     const pro = new Promise((resolve, reject) => {
//         if(x === 6){
//             resolve("Success");
//         }
//         else{
//             reject(new Error("Failed"));
//         }
//     })


//     pro.then((data) => {
//         console.log(data);
//     }).catch((err) => {
//         console.log(err);
//     });
// }


// testPromise(6);

function add(a, b){
    return a + b;
}

function testPromise(x){
    const pro = new Promise((resolve, reject) => {
        if(x === 6){
            resolve(add(5,6));
        }
        else{
            reject(add(2,3));
        }
    })

    pro.then((data) => {
        console.log(data);
    }).catch((err) => {
        console.log(err);
    });
}

testPromise(1);

