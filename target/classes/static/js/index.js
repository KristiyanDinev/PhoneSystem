console.log("Hi");


async function delete_phone(phone) {
    let formData = new FormData()
    formData.append("phone", phone)


    const res = await fetch("http://127.0.0.1:8080/delphone", {
            method: "POST",
            body: formData,
            redirect: 'follow',
        })

    if (res.status === 200) {
       window.location.reload()
    }
}