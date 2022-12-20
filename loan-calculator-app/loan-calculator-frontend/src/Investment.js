import {useState} from "react";
import axios from "axios";
const Investment = () => {
    const currentDate = new Date();

    const [name, setName] = useState('');
    const [contractDate, setContractDate] = useState(currentDate.toISOString().slice(0, 10));
    const [period, setPeriod] = useState(24);
    const [amount, setAmount] = useState(200_000);
    const [interestRate, setInterestRate] = useState(5);
    const [scheduleType, setScheduleType] = useState("monthly");
    const [investmentValue, setInvestmentValue] = useState(100_000);
    const [ownContribution, setOwnContribution] = useState(20_000);
    const [comission, setComission] = useState(3);

    const handleSubmit = (e) => {
        console.log("handle submit");
        e.preventDefault();
        const schedule = getSchedule();
    }

    const getSchedule = async() => {
        const schedule = {name, contractDate, period, amount, interestRate, scheduleType};
        console.log("get schedule");
        console.log(schedule);

        axios.post(
            'http://localhost:8080/api/agro',
            schedule,
            {responseType:'blob'})
            .then(res => printSchedule(res.data));
    }

    const printSchedule = (schedule) => {
        console.log(schedule);
        window.open(URL.createObjectURL(schedule));
        console.log("print schedule");
    }

    return (
        <div className="agro">
            <h2>Kredyt inwestycyjny</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">Imię i nazwisko:</label>
                <input type="text"
                       id="name"
                       value={name}
                       onChange={(e) => setName(e.target.value)}
                       required
                />
                <label>Data zawarcia umowy</label>
                <input type="date" lang="pl-PL"
                       min={currentDate.toISOString().slice(0, 10)}
                       value={contractDate}
                       onChange={(e) => setContractDate(e.target.value)}
                       required/>
                <label>Okres finansowania (w miesiącach)</label>
                <input type="integer"
                       value={period}
                       onChange={(e) => setPeriod(e.target.value)}
                       required/>
                <label>Wartość inwestycji</label>
                <input type="float"
                       value={investmentValue}
                       onChange={(e) => setInvestmentValue(e.target.value)}
                       required/>
                <label>Wkład własny</label>
                <input type="float"
                       value={ownContribution}
                       onChange={(e) => setOwnContribution(e.target.value)}
                       required/>
                <label>Kwota kredytu</label>
                <input type="float"
                       value={amount}
                       onChange={(e) => setAmount(e.target.value)}
                       required/>
                <label>Oprocentowanie kredytu</label>
                <input type="float"
                       value={interestRate}
                       onChange={(e) => setInterestRate(e.target.value)}
                       required/>
                <label>Prowizja</label>
                <input type="float"
                       value={comission}
                       onChange={(e) => setComission(e.target.value)}
                       required/>
                <label>Typ harmonogramu spłat</label>
                <select
                    value={scheduleType}
                    onChange={(e) => setScheduleType(e.target.value)}>
                    <option value="monthly">Miesięczny</option>
                    <option value="quarterly">Kwartalny</option>
                </select>
                <button>Oblicz</button>


            </form>

        </div>
    );
}

export default Investment;