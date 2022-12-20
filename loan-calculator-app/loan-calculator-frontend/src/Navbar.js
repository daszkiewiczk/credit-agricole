import {Link, NavLink} from "react-router-dom";

const Navbar = () => {
    return (
        <nav>
                <div className="links">
                    <NavLink to="/agro">Pożyczka Agro</NavLink>
                    <NavLink to="/investment">Kredyt inwestycyjny</NavLink>
                </div>
        </nav>
    );
}
export default Navbar;