import PropTypes from 'prop-types';

function Navbar(props) {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-dark">
            <a className="navbar-brand text-light" href="/">{ props.title }</a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                    <li className="nav-item active">
                        <a className="nav-link text-light" href="/">Home <span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link text-light" href="/">{ props.aboutText }</a>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

Navbar.propTypes = {
    title: PropTypes.string,
    aboutText: PropTypes.string
};

export default Navbar;
