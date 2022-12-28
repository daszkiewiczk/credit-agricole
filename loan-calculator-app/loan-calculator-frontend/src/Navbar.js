import {Link, NavLink} from "react-router-dom";

const Navbar = () => {
    return (
        <nav>
            <div className="links">
                <div>keel pulled this image from docker hub</div>
                <NavLink
                    style={({isActive}) =>
                        isActive ? ({fontSize: 20}) : ({fontSize: 15})
                    }
                    to="/agro">Pożyczka Agro</NavLink>
                <NavLink
                    style={({isActive}) =>
                        isActive ? ({fontSize: 20}) : ({fontSize: 15})
                    }
                    to="/investment">Kredyt inwestycyjny</NavLink>
            </div>
        </nav>
    );
}
export default Navbar;