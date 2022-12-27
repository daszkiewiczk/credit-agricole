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
        if (scheduleType === "QUARTERLY" && period % 3 !== 0) {
            console.log("Nieprawidłowy okres spłaty");
            alert("Okres kredytowania musi być podzielny przez 3 w przypadku harmonogramu kwartalnego");
            return;
        }
        const schedule = getSchedule();

    }

    const getSchedule = async () => {
        const schedule = {name, contractDate, period, amount, interestRate, scheduleType};


        axios.post(
            'http://localhost/api/agro',
            schedule,
            {responseType: 'blob'})
            .then(res => printSchedule(res.data));
    }

    const printSchedule = (schedule) => {

        window.open(URL.createObjectURL(schedule));

    }

    return (
        <div className="agro">
            <h2>Pożyczka Agro</h2>
            <form onSubmit={handleSubmit}>

                <label htmlFor="name">Imię i nazwisko:</label>
                <input type="text"
                       id="name"
                       value={name}
                       onChange={
                           (e) => {
                               const re = /^[a-zA-ZżźćńółęąśŻŹĆĄŚĘŁÓŃ ]+$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setName(e.target.value)
                               }
                               ;
                           }}
                       required//
                />
                <label>Data zawarcia umowy</label>
                <input type="date" lang="pl-PL"
                       min={currentDate.toISOString().slice(0, 10)}
                       value={contractDate}
                       onChange={(e) => setContractDate(e.target.value)}
                       required/>
                <label>Okres finansowania (w miesiącach)</label>
                <input type="number"
                       value={period}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]{0,8}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setPeriod(e.target.valueAsNumber)
                               }
                               ;
                           }}
                       required/>
                <label>Kwota kredytu</label>
                <input type="float"
                       value={amount}
                       onChange={
                           (e) => {
                               const re = /^[1-9][0-9]*[\.,]{0,1}[0-9]{0,2}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setAmount(e.target.value.replace(",", "."));
                               }
                               ;
                           }}
                       required/>
                <label>Oprocentowanie kredytu</label>
                <input type="float"
                       value={interestRate}
                       onChange={
                           (e) => {
                               const re = /^[0-9]*[\.,]{0,1}[0-9]{0,3}$/;
                               if (e.target.value === "" || re.test(e.target.value)) {
                                   setInterestRate(e.target.value.replace(",", "."));
                               }
                               ;
                           }}
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