import React from 'react';
import Navbar from './component/Navbar';
import TextForm from './component/TextForm';

function App() {
    return (
        <>
            {/* Navbar start */ }
            <Navbar title="TextUtils" aboutText="About us" /> {/* Pass the props */ }

            <div className="div container">
                <TextForm heading="Enter your text to analyze" />
            </div>

        </>
    );
};

export default App;
