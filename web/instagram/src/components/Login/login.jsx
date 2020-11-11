import React from 'react'
import { Link } from "react-router-dom";
import { signIn } from '../../../services/api'


const LabelInput = ({labelText,inputType, inputPlaceholder,inputID,tagetInput,value,onChange=()=>{},maxLength="100" }) => 
{
    return(
        <div className="form-group">
            <label htmlFor={tagetInput} className="small">{labelText}</label>
            <input
                type={inputType}
                className="form-control"
                id={inputID}
                placeholder={inputPlaceholder}
                value={value}
                onChange={onChange}
                required
                maxLength={maxLength}
            />
        </div>
    );
}



const EmailLabelInput = ({ labelText="Email", inputPlaceholder="ejemplo@dominio.com",inputID='login-email',value,onChange=()=>{} }) => 
{
    return(
        <LabelInput labelText={labelText}
                    inputType="email"
                    inputPlaceholder={inputPlaceholder}
                    inputID={inputID}
                    tagetInput="inputLoginEmail"
                    value={value}
                    onChange={onChange}
                     />
    );
}



const PasswordLabelInput = ({ labelText, inputPlaceholder,inputID='login-password',value,onChange=()=>{} }) => 
{
    return(
        <LabelInput labelText={labelText}
                    inputType="password"
                    inputPlaceholder={inputPlaceholder}
                    inputID={inputID}
                    tagetInput="inputLoginPassword"
                    value={value}
                    onChange={onChange}
            
        />
    );
}

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            error: '',
            modalEvent: false,
            isLoading: false
        };
        this.changeUsername = this.changeUsername.bind(this);
        this.changePassword = this.changePassword.bind(this);
        this.executeSignIn = this.executeSignIn.bind(this);
    }

    changeUsername(event) {
        this.setState({ username: event.target.value });
    }

    changePassword(event) {
        this.setState({ password: event.target.value });
    }

    executeSignIn(event) {
        this.setState({ isLoading: true})
        setTimeout(() => { this.login() }, 0); // TODO dejar como estaba
    }

    login() {
        signIn({ email: this.state.username, password: this.state.password })
            .then((data) => {
                    this.setState({ isLoading: false })
                    this.props.history.push('/profile', { state: {...data}})
            })
            .catch((err) => {
                const error = err && err.response && err.response.data && err.response.data.title
                this.setState({ isLoading: false, error: error || "Error de conexión" })
                this.toggleEvent();
            });
    }

    toggleEvent = () => {
        this.setState({
          modalEvent: !this.state.modalEvent
        });
      }


    render() {
        const showSpinner = (
            <>
                <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                <span className="sr-only"></span>
                Iniciando
            </>
        )
        return (
            <>
                <div className="vh-10 d-flex align-items-center justify-content-center m-5">
                    <div className="card shadow-sm border-secondary shadow">

                        <form className="card-body">

                            <EmailLabelInput inputID="login-email" value={this.state.username} onChange={this.changeUsername} />
                            <PasswordLabelInput labelText="Contraseña" inputPlaceholder="Contraseña" inputID="login-password" value={this.state.password} onChange={this.changePassword} />

                            <div className="form-group text-center col-xs-3 ">
                                    <button className="btn btn-primary btn-block"
                                        type="button"
                                        onClick={this.executeSignIn}
                                    >
                                    {this.state.isLoading ? showSpinner : "Acceder"}
                                    </button>
                                <Link to="/register" className="btn btn-secondary btn-block">Registrar</Link>
                            </div>
                        </form>
                    </div>
                </div>

                    
             </>
        );
    }
}


export default Login;