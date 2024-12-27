import React, { useState } from 'react';

export default function TextForm(props) {
    const [text, setText] = useState();

    const handelUpperCase = () => {
        if (text && text.trim() !== "") {
            setText(text.toUpperCase())
        } else {
            alert("Text box is empty. Please fill out the text box.")
        }
    };

    const heandelLowerCase = () => {
        if (text && text.trim() !== "") {
            setText(text.toLowerCase())
        } else {
            alert("Text box is empty. Please fill out the text box.")
        }
    }

    const handelOnchange = (event) => {
        setText(event.target.value);
    };

    const clearText = () => {
        setText("");
    };

    return (
        <div className='mb-3 bg-dark m-5 h-50'>
            <div className='container'>
                <h1 className='text-light text-center pt-3'>{ props.heading }</h1>
                <div className='m-5 pb-3'>
                    <textarea className='form-control ' id='myBox' value={ text } onChange={ handelOnchange } row='5' placeholder='Enter your text here'></textarea>
                </div>
                <div className='pb-4 pl-5' >
                    <button type="button" className="btn btn-primary" onClick={ handelUpperCase }>Convert to upper case</button>
                    <button type="button" class="btn btn-success ml-2" onClick={ heandelLowerCase }>Convert to lower case</button>
                    <button type="button" className="btn btn-danger ml-2" onClick={ clearText }>Clear</button>
                </div>
            </div>
        </div>
    );
}
