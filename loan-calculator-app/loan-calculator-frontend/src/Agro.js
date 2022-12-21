import {useState} from "react";
import axios from "axios";
const Agro = () => {
    const currentDate = new Date();

    const [name, setName] = useState('');
    const [contractDate, setContractDate] = useState(currentDate.toISOString().slice(0, 10));
    const [period, setPeriod] = useState(24);
    const [amount, setAmount] = useState(20_000);
    const [interestRate, setInterestRate] = useState(5);
    const [scheduleType, setScheduleType] = useState("MONTHLY");

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
            'http://localhost/api/agro',
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
            <h2>Pożyczka Agro</h2>
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
                <label>Typ harmonogramu spłat</label>
                <select
                    value={scheduleType}
                    onChange={(e) => setScheduleType(e.target.value)}>
                    <option value="MONTHLY">Miesięczny</option>
                    <option value="QUARTERLY">Kwartalny</option>
                </select>
                <button>Oblicz</button>


            </form>

        </div>
    );
}

export default Agro;